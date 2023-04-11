# Guide to modify Kernel to unlock pKVM 

### This guide assume you know how to compile android kernel 

Pixel phones use a protected kernel-based virtual machine (pKVM), which is secure but limits low-level memory access. 

As a result, when using Qemu, the guest OS is not allowed to write pflash, which causes edk2 to fail to write NVRAM. Additionally, Windows may encounter a Blue Screen of 0xc000021a when booting up.

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/bsod.png?raw=true)

To use edk2 and Windows, the KVM needs to be set to unprotected mode, which unlocks memory access to the guest OS. 

The KVM mode flag is set within the boot cmdline, where kernel parameters can be modified to enable or disable KVM. "kvm-arm.mode=protected" enables protected KVM, "kvm-arm.mode=nvhe" enables unprotected KVM, and "kvm-arm.mode=none" disables KVM.

Current KVM status can be found using `cat /proc/cmdline`
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/cmdline_kvm_protected.png?raw=true)


To use unprotected KVM, modify the boot cmdline from the Android kernel source. The KVM flag is configured in gki_defconfig -> CONFIG_CMDLINE. 

In Pixel's kernel, these files are located at `aosp/arch/arm64/configs/gki_defconfig` for AOSP kernel and `private/gs-google/arch/arm64/configs/gki_defconfig` for Google Pixel kernel.

Modify the config to "kvm-arm.mode=nvhe" and compile the kernel to get an unlock KVM.

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/defconfig.png?raw=true)


Check KVM status again; now KVM is unlocked
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/cmdline_kvm_nvhe.png?raw=true)
