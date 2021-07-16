package net.minecraft.stats;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.StatCollector;

public class Achievement extends StatBase
{
    /**
     * Is the column (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayColumn;

    /**
     * Is the row (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayRow;

    /**
     * Holds the parent achievement, that must be taken before this achievement is avaiable.
     */
    public final Achievement parentAchievement;

    /**
     * Holds the description of the achievement, ready to be formatted and/or displayed.
     */
    private final String achievementDescription;

    /**
     * Holds a string formatter for the achievement, some of then needs extra dynamic info - like the key used to open
     * the inventory.
     */
    private IStatStringFormat statStringFormatter;

    /**
     * Holds the ItemStack that will be used to draw the achievement into the GUI.
     */
    public final ItemStack theItemStack;

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    private boolean isSpecial;

    public Achievement(String statIdIn, String unlocalizedName, int column, int row, Item itemIn, Achievement parent)
    {
        this(statIdIn, unlocalizedName, column, row, new ItemStack(itemIn), parent);
    }

    public Achievement(String statIdIn, String unlocalizedName, int column, int row, Block blockIn, Achievement parent)
    {
        this(statIdIn, unlocalizedName, column, row, new ItemStack(blockIn), parent);
    }

    public Achievement(String statIdIn, String unlocalizedName, int column, int row, ItemStack stack, Achievement parent)
    {
        super(statIdIn, new ChatComponentTranslation("achievement." + unlocalizedName, new Object[0]));
        this.theItemStack = stack;
        this.achievementDescription = "achievement." + unlocalizedName + ".desc";
        this.displayColumn = column;
        this.displayRow = row;

        if (column < AchievementList.minDisplayColumn)
        {
            AchievementList.minDisplayColumn = column;
        }

        if (row < AchievementList.minDisplayRow)
        {
            AchievementList.minDisplayRow = row;
        }

        if (column > AchievementList.maxDisplayColumn)
        {
            AchievementList.maxDisplayColumn = column;
        }

        if (row > AchievementList.maxDisplayRow)
        {
            AchievementList.maxDisplayRow = row;
        }

        this.parentAchievement = parent;
    }

    /**
     * Initializes the current stat as independent (i.e., lacking prerequisites for being updated) and returns the
     * current instance.
     */
    public Achievement initIndependentStat()
    {
        this.isIndependent = true;
        return this;
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public Achievement setSpecial()
    {
        this.isSpecial = true;
        return this;
    }

    /**
     * Register the stat into StatList.
     */
    public Achievement registerStat()
    {
        super.registerStat();
        AchievementList.achievementList.add(this);
        return this;
    }

    /**
     * Returns whether or not the StatBase-derived class is a statistic (running counter) or an achievement (one-shot).
     */
    public boolean isAchievement()
    {
        return true;
    }

    public IChatComponent getStatName()
    {
        IChatComponent ichatcomponent = super.getStatName();
        ichatcomponent.getChatStyle().setColor(this.getSpecial() ? EnumChatFormatting.DARK_PURPLE : EnumChatFormatting.GREEN);
        return ichatcomponent;
    }

    public Achievement func_150953_b(Class <? extends IJsonSerializable > p_150953_1_)
    {
        return (Achievement)super.func_150953_b(p_150953_1_);
    }

    /**
     * Returns the fully description of the achievement - ready to be displayed on screen.
     */
    public String getDescription()
    {
        return this.statStringFormatter != null ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.achievementDescription)) : StatCollector.translateToLocal(this.achievementDescription);
    }

    /**
     * Defines a string formatter for the achievement.
     *  
     * @param statStringFormatterIn 1.8.9
     */
    public Achievement setStatStringFormatter(IStatStringFormat statStringFormatterIn)
    {
        this.statStringFormatter = statStringFormatterIn;
        return this;
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public boolean getSpecial()
    {
        return this.isSpecial;
    }
}
