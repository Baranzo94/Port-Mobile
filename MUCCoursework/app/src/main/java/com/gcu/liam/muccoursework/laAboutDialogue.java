package com.gcu.liam.muccoursework;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Liam on 05/12/2015.
 */
//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class laAboutDialogue extends DialogFragment
{
    // This is class is responsible for the creation of the About Dialogue which is accessable through the menu
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        AlertDialog.Builder laAboutDialogue = new AlertDialog.Builder(getActivity());
        laAboutDialogue.setMessage("This app will randomly pick a lunch time venue").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });

        laAboutDialogue.setTitle("About");

        laAboutDialogue.setIcon(R.drawable.ic_menu_action_about);

        return laAboutDialogue.create();
    }
}
