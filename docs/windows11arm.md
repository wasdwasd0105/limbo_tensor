# Running Windows 11 ARM64 on Limbo

## Requirements
1. Flash an unlocked KVM Magisk Moudle (https://github.com/wasdwasd0105/limbo_tensor/blob/master/docs/unlock-pkvm.md) [download](https://github.com/wasdwasd0105/limbo_tensor/releases/download/v0.5.0/unlock_kvm_magisk.zip).
2. Obtain a Windows system drive file.
3. Acquire UTM's SPICE ARM64 driver [link](https://docs.getutm.app/guest-support/windows/).  

## Installation

Currently, Limbo cannot boot the Windows Installer ISO. Therefore, you have two options to acquire a Windows system drive:

1. Use an M1 Mac and UTM to obtain a Windows qcow2 drive file.
2. Download the Windows Insider Preview ARM64 vmdk.

## Limbo Setup

Here are the recommended settings for Limbo:

>### Board 
1. Machine Type: virt
2. CPU Model: Deafult or host
3. CPU Cores: 4-6 Cores
4. RAM Memory: 3072 MB or larger (4096 MB or more is better)
5. Enable UEFI
6. Enable KVM
7. Enable Unlocked UEFI
8. Enable 100% Success Mode

>### Disks
- Use the Windows system drive you acquired.
   
>### Removable
- If you haven't installed the SPICE driver, you can mount the SPICE tools ISO here.

>### Boot
- Do not modify.
   
>### Graphics
- Use virtio-ramfb.

>### Network
1. Network: Use 'User.'
2. Network Card: Use virtio (install SPICE driver first).
3. Host Forward: Forward RDP port with `tcp:3389:3389`.


## Troubleshooting

### System is slow
- Click the 🚀 icon in Limbo to enable all cores after the system boots up (enabling while booting is acceptable if the wait time is too long).

### No video or network driver
- Install UTM's SPICE driver.

### Could not run x86 apps

- Disable the application cache in the program properties, as shown in the image below:

![](https://github.com/wasdwasd0105/limbo_tensor/blob/master/pics/disable_application_cache.png?raw=true)

### Apps open slow and too many error report programs in task manager

- Disable the Windows Error Reporting service. It runs in x86 mode, but you cannot set Compatibility properties, causing an infinite crash loop.
