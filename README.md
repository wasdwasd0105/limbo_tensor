# QEMU KVM (Limbo) For Tensor-based devices 

Discord: [https://discord.gg/4hWgR6tpb7](https://discord.gg/gHJn4r5WtA)

Limbo for Tensor is a QEMU-based hypervisor for KVM-enabled AArch64 devices such as Google Pixel 6,7,8,9 series.

To use Windows and UEFI, unlock the pKVM first https://github.com/wasdwasd0105/limbo_tensor/blob/master/docs/unlock-pkvm.md

Limbo leverages the Kernel KVM feature from the Exception Level 2 hypervisor privilege level.

Currently, Google Tensor SoC supports this feature. ROOT access is REQUIRED to access Kernel KVM.

**please consider becoming a [:heart: Sponsor via PayPal](https://www.paypal.com/donate/?business=UZAK3WFV233ML&no_recurring=0&item_name=Help+me+build+more+project%21&currency_code=USD) or support us via [:coffee: Ko-fi](https://ko-fi.com/wasdwasd0105).**  

Many MTK Dimensity（天玑) phones can run this program if KVM modules are compiled into the kernel. See [here](https://www.bilibili.com/video/BV17k4y1E7W7/)

Flash Unlock KVM [Magisk module](https://github.com/wasdwasd0105/limbo_tensor/releases/download/v0.5.0/unlock_kvm_magisk.zip) can run Windows 11 on ARM [See guide here](https://github.com/wasdwasd0105/limbo_tensor/blob/master/docs/windows11arm.md)

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/win11arm.png?raw=true)

CPU-Z x64 scores over 900 on Google Pixel 7.

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/win11_cpuz_x64.png?raw=true)

Limbo running Ubuntu ARM64 VM

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/Screenshot_20221024-022640.png?raw=true)

Passmark CPU & Mem test on Google Pixel 7

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/passmark_8cores.png?raw=true)

USB Passthrough Demo: Sharing Netgear USB Wi-Fi to Ubuntu VM.

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/usb_demo.png?raw=true)



USB Passthrough Speed: Transferring a large file to

Ubuntu VM
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/usb_demo2.png?raw=true)

Windows 11 VM
![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/usb_demo_win11.png?raw=true)

## Features

>### User Interface 
1. Display: Can be output via VNC on port `5900` for both LAN and local connections.
2. Serial Out: can be out via telent:
    - none
    - local only on port `8021` (telnet:127.0.0.1:8021)
    - local and LAN on `8021` (telnet:0:8021)
3. Mouse:
   - usb-tablet (good on VNC)
   - usb-mouse

>### Board 
1. Machine Type: use virt
2. CPU Model: use max or host
3. CPU Core: up to 8
4. RAM Momory: up to 8192MB
5. Enable UEFI:
   - Enable: can boot generic Linux ISO image
   - Disable：can only boot via linux Kernel and Initrd
6. Enable KVM:
   - Enable: can use KVM virtualization solution
   - Disable: disable virtualization mode
7. Full UEFI:
   - Enable: Loads a full version UEFI firmware that can save EFI variables to NVRAM pflash. Requires [unlock pKVM](https://github.com/wasdwasd0105/limbo_tensor/blob/master/docs/unlock-pkvm.md).
   - Disable: Loads a modified version that bypasses NVRAM write. Can run on protected KVM mode.
8. 100% Success Mode:
   - Enable: Will run Qemu only on CPU 0-3 to avoid boot failure. You can click 🚀 icon after UEFI loaded to enable all CPU cores
   - Disable: Use all cores on Qemu start. May have stack overflow error on UEFI firmware

>### Disks: used to store data drive

>### Removeable 
1. CDROM: can be used to install OS. Disable it after installation

>### Boot (Do not modify if UEFI is enabled)

>### Graphics
1. Video Display
    - virtio-ramfb: UTM's ramfb driver, can be used to run Windows and Linux
    - virtio-gpu-pci: Linux only
    - nographic: disable display

>### Network
1. Network: None or User Mode works
2. Network Card: Choose virtual network card (use virtio)
3. DNS Server: Current is 8.8.8.8 (may not work, need more test)
4. Host Forword: working now (eg tcp:4444:3389)

>### USB
1. Can Passthrough USB 3.0 Device to VMs
2. Input device path follows (BusID).(DeviceID) You can use 3rd APPs eg (https://play.google.com/store/apps/details?id=usbdevicem.indvel.app.usbdevicemanager) to find out.
3. If the Device Path is "/dev/bus/usb/003/004", You input is "3.4"
4. You need to connect the device before the VM starts. You can disconnect devices while running the VMs.


## Todo List
- [x] KVM support
- [x] UEFI support
- [x] USB passthrough
- [x] Windows on ARM
- [x] virtio-ramfb for Windows (from UTM)
- [x] FIX network options
- [x] FIX nvram write option
- [ ] GL support
- [ ] Add Audio options
- [ ] DNS passthrough (current hardcoded under `/data/data/com.limbo.emu.main.arm/cache/limbo/resolv.conf`)
- [ ] Support contorl qemu
- [ ] Spice 


