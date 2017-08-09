package kyoto.freeprojects.oldbigbuddha.number_of_cases;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by BigBuddha on 2017/08/09.
 */

public class ThemeUtils {

    public final static int THEME_DARK  = 0;
    public final static int THEME_LIGHT = 1;

    public final static String PREFERENCE = "ThemeData";
    public final static String THEME      = "theme";

//
//    public static void changeTheme(Activity activity, int theme) {
//        sTheme = theme;
//        activity.finish();
//        activity.startActivity(new Intent(activity, activity.getClass()));
//    }

    public static void setTheme(Activity activity, int theme) {
        switch (theme) {
            default:
            case THEME_LIGHT: {
                activity.setTheme(R.style.LightTheme);
                break;
            }
            case THEME_DARK: {
                activity.setTheme(R.style.DarkTheme);
                break;
            }
        }
    }
}
