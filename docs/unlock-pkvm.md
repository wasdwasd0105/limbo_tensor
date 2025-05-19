# There are multiple methods of unlocking pKVM

Pixel phones use a protected kernel-based virtual machine (pKVM), which is secure but limits low-level memory access. 

As a result, when using Qemu, the guest OS is not allowed to write pflash, which causes edk2 to fail to write NVRAM. 

## If pKVM is not unlocked, the vnc player will show
_**"Guest has not initialized the display (yet)."**_
and/or windows will encounter a Blue Screen of 0xc000021a when booting up.
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/bsod.png?raw=true)

To use edk2 and Windows, the KVM needs to be set to unprotected mode, which unlocks memory access to the guest OS. 

The KVM mode flag is set within the boot cmdline, where kernel parameters can be modified to enable or disable KVM. "kvm-arm.mode=protected" enables protected KVM, "kvm-arm.mode=nvhe" enables unprotected KVM, and "kvm-arm.mode=none" disables KVM.

Current KVM status can be found using `cat /proc/cmdline`
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/cmdline_kvm_protected.png?raw=true)



## 1. You can install the Magik module (no longer work after Android 14)
[https://github.com/wasdwasd0105/limbo_tensor/releases/](https://github.com/wasdwasd0105/limbo_tensor/releases/)

## 2. Guide to use fastboot to unlock pKVM
For that you firstly need to start into the bootloader.

Run `fastboot oem pkvm enable` (to make sure pkvm is enabled)

Run `fastboot oem cmdline add kvm-arm.mode=nvhe` (to make sure that pkvm is accessible)

Run `fastboot oem cmdline show` (to check if its enabled now)

If everything worked out, reboot to system and it should work,
even though "`cat /proc/cmdline`" might still output _"kvm-arm.mode=protected"_

NOTE: On pixel 8 (Pro/a) you need to be on Android 14 QPR3 or higher or the USB capabilities of the phone will be gone (except on the bootloader/ charging/ adb works anytime)

If you want to disable pkvm again, run:

fastboot oem cmdline del kvm-arm.mode=nvhe (the command has "del" now to delete the entry)


## 3. Guide to modify Kernel to unlock pKVM 

### This guide assume you know how to compile android kernel 


To use unprotected KVM, modify the boot cmdline from the Android kernel source. The KVM flag is configured in gki_defconfig -> CONFIG_CMDLINE. 

These files are located at `aosp/arch/arm64/configs/gki_defconfig` for AOSP kernel and `private/gs-google/arch/arm64/configs/gki_defconfig` for Google Pixel kernel.

Modify the config from "kvm-arm.mode=protected" to "kvm-arm.mode=nvhe" and compile the kernel to get an unlock KVM.

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/defconfig.png?raw=true)


Check KVM status again; now KVM is unlocked
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/cmdline_kvm_nvhe.png?raw=true)
