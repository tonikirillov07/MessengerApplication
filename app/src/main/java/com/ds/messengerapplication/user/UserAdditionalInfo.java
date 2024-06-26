package com.ds.messengerapplication.user;

import static com.ds.messengerapplication.user.UserAdditionalInfo.DefaultValues.*;

import androidx.annotation.NonNull;

import com.ds.messengerapplication.util.Colors;
import com.ds.messengerapplication.util.Utils;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class UserAdditionalInfo {
    private float saturation;
    private float contrast;
    private float brightness;
    private boolean useScrollBars;
    private boolean useSounds;
    private String theme;
    private int userAvatarColor;
    private String dateOfRegistration;

    public UserAdditionalInfo() {
    }

    public UserAdditionalInfo(float saturation, float contrast, float brightness, boolean useScrollBars, boolean useSounds, String theme, int userAvatarColor, String dateOfRegistration) {
        this.saturation = saturation;
        this.contrast = contrast;
        this.brightness = brightness;
        this.useSounds = useSounds;
        this.useScrollBars = useScrollBars;
        this.theme = theme;
        this.userAvatarColor = userAvatarColor;
        this.dateOfRegistration = dateOfRegistration;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public float getContrast() {
        return contrast;
    }

    public void setContrast(float contrast) {
        this.contrast = contrast;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public boolean isUseScrollBars() {
        return useScrollBars;
    }

    public void setUseScrollBars(boolean useScrollBars) {
        this.useScrollBars = useScrollBars;
    }

    public boolean isUseSounds() {
        return useSounds;
    }

    public void setUseSounds(boolean useSounds) {
        this.useSounds = useSounds;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getUserAvatarColor() {
        return userAvatarColor;
    }

    public void setUserAvatarColor(int userAvatarColor) {
        this.userAvatarColor = userAvatarColor;
    }


    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @NonNull
    @Contract(" -> new")
    public static UserAdditionalInfo getWithDefaultValues(){
        return new UserAdditionalInfo(DEFAULT_SATURATION, DEFAULT_CONTRAST, DEFAULT_BRIGHTNESS, DEFAULT_USE_SCROLL_BARS, DEFAULT_USE_SOUNDS, DEFAULT_THEME, Colors.getRandomColor(), AUTO_DATE);
    }

    public abstract static class DefaultValues{
        public static final float DEFAULT_SATURATION = 0.5f;
        public static final float DEFAULT_CONTRAST = 0.5f;
        public static final float DEFAULT_BRIGHTNESS = 0.5f;
        public static final boolean DEFAULT_USE_SCROLL_BARS = true;
        public static final boolean DEFAULT_USE_SOUNDS = true;
        public static final String DEFAULT_THEME = "Dark Theme";
        public static final String AUTO_DATE = Utils.getDate();
    }
}
