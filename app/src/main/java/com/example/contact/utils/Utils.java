package com.example.contact.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: YH
 * @date: 2021/12/6
 * @desc:
 */
public class Utils {

    public static void showShortToast(Context context, CharSequence sequence) {
        Toast.makeText(context, sequence, Toast.LENGTH_SHORT).show();
    }

    public static boolean Match(String regex, String s) {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(s);
        return matcher.matches();
    }

}
