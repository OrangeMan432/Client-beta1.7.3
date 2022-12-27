package dozer.ui.impl.settings;

import dozer.setting.Setting;
import dozer.setting.impl.SettingNumber;
import dozer.ui.impl.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.src.gui.Gui;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends SettingComponent {

    private double x;
    private double y;
    private SettingNumber numberSetting;

    private boolean dragging = false;

    public Slider(Setting s, Button parent) {
        super(s, parent);
        this.numberSetting = (SettingNumber) s;
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, double x, double y) {
        this.x = x;
        this.y = y;

        if (this.dragging) {
            double diff = mouseX - (this.x);
            double min = this.numberSetting.getMin();
            double max = this.numberSetting.getMax();

            if (diff == 0) {
                this.numberSetting.setValue(this.numberSetting.getMin());
            } else {
                double newValue = this.roundToPlace(((diff / (100)) * (max - min) + min));
                newValue = this.roundToIncrement(newValue, this.numberSetting.getIncrement());
                if (newValue > this.numberSetting.getMax()) {
                    newValue = this.numberSetting.getMax();
                }
                if (newValue < this.numberSetting.getMin()) {
                    newValue = this.numberSetting.getMin();
                }
                this.numberSetting.setValue(newValue);
            }
        }

        Gui.drawRect(x, y, x + 100, y + 14, new Color(16, 16, 16, 200).getRGB());
        Gui.drawRect(x, y + 13, x + (this.numberSetting.getValue() / this.numberSetting.getMax() * 100), y + 14, new Color(255, 255, 255, 200).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.numberSetting.getName(), this.x + 2, this.y + 3, -1);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.numberSetting.getValue() + "", this.x + 100 - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.numberSetting.getValue() + "") - 2, this.y + 2, -1);
        return 14;
    }

    public double roundToPlace(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double roundToIncrement(double value, double increment) {
        double v = (double) Math.round(value / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        if (button == 0) {
            if (isHovered(x, y, this.x, this.y)) {
                dragging = true;
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y, int button) {
        if (button == 0) {
            dragging = false;
        }
    }

}
