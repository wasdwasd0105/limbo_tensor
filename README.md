# QEMU KVM (Limbor) For Tensor-based devices 

Limbo for Tensor is a QEMU-based Hypervisor for Tensor-based Google Pixel devices such as Pixel 6 & 7 series.

Limbo utilizes Kernal KVM feature from Exception Level 2 hypervisor privilege level. 

Currently only Google Tensor SOC support the feature. ROOT access is REQUIRED to access Kernal KVM.

***

## Features

>### User Interface 
1. Display: can  be out via VNC on port `5900` by LAN and local
2. Serial Out: can be out via telent:
    - none
    - local only on port `8021` (telnet:127.0.0.1:8021)
    - local and LAN on `8021` (telnet:0:8021)
3. Mouse:
   - usb-table (good on VNC)
   - usb-mouse

>### Board 
1. Machine Type: use virt
2. CPU Model: use max or host
3. CPU Core: up to 8
4. RAM Momory: up to 8192MB
5. Enable UFEI:
   - Enable: can boot generic Linux ISO image
   - Disable：can only boot via linux Kernel and Initrd
6. Enable KVM:
   - Enable: can use KVM virtualization solution
   - Disable: disable virtualization mode

>### Disks: used to store data drive

>### Removeable 
1. CDROM: can be used to install OS. Disable it after installation
2. SD Card: Do not use and will be removed later

>### Boot (Do not modify if UEFI is enabled)

>### Graphics
1. Video Display
    - virtio-gpu-pci: recommended on Linux
    - ramfb: required to boot Windows (not working)
    - nographic: disable display

>### Network
1. Network: only None or User Mode works
2. Network Card: Choose virtual network card
3. DNS Server: Current is 8.8.8.8 (may not work, need more test)
4. Host Forword: not working


## Todo List
- [x] KVM support
- [x] UEFI support
- [ ] ramfb for Windows
- [ ] GL support
- [ ] USB passthrough
- [ ] FIX network options
- [ ] FIX nvram write option
- [ ] Add Audio options
- [ ] Windows on ARM
- [ ] DNS passthrough (current hardcoded under `/data/data/com.limbo.emu.main.arm/cache/limbo/resolv.conf`)
- [ ] Support contorl qemu
- [ ] Spice 


