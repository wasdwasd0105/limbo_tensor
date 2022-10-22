package com.limbo.emu.main.arm;

import android.os.Bundle;

import com.max2idea.android.limbo.log.Logger;
import com.max2idea.android.limbo.main.Config;
import com.max2idea.android.limbo.main.LimboActivity;
import com.max2idea.android.limbo.links.LinksManager;
import com.max2idea.android.limbo.main.LimboApplication;

public class LimboEmuActivity extends LimboActivity {

    public void onCreate(Bundle bundle){
        LimboApplication.arch = Config.Arch.arm64;
        Config.clientClass = this.getClass();
        Config.enableKVM = true;
        Config.enableEmulatedFloppy = false;
        Config.enableEmulatedSDCard = true;

        // It is UEFI option
        Config.enableUEFI = true;
        Config.machineFolder = Config.machineFolder + "other/arm_machines/";
        Config.osImages.put(getString(R.string.UbuntuArmLinux), new LinksManager.LinkInfo(getString(R.string.UbuntuArmLinux),
                getString(R.string.UbuntuArmLinux),
                "https://ubuntu.com/download/server/arm",
                LinksManager.LinkType.ISO));
        super.onCreate(bundle);
        //TODO: change location to something that the user will have access outside of limbo
        //  like internal storage
        Logger.setupLogFile("/limbo/limbo-arm-log.txt");
    }

    protected void loadQEMULib(){

        try {
            System.loadLibrary("qemu-system-arm");
        } catch (Error ex) {
            System.loadLibrary("qemu-system-aarch64");
        }

    }
}
