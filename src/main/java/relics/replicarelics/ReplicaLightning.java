package chronomuncher.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.helpers.PowerTip;

import com.badlogic.gdx.graphics.Texture;
import basemod.abstracts.CustomRelic;
import chronomuncher.ChronoMod;
import chronomuncher.orbs.*;
import chronomuncher.actions.*;

public class ReplicaLightning extends CustomRelic {
    public static final String ID = "Lightning?";

    public ReplicaLightning() {
        super(ID, new Texture("chrono_images/relics/Lightning.png"), new Texture("chrono_images/relics/outline/Lightning.png"), RelicTier.SPECIAL, LandingSound.CLINK);

        this.tips.add(new PowerTip("Lightning", "Draws a random #ySkill card from your deck at the start of your turn. Won't shatter on its own."));
        this.initializeTips();
    }

    @Override
    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }
    
    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(1));
        AbstractDungeon.actionManager.addToBottom(new ChronoChannelAction(new UnlockedLightning(false)));
    }

    @Override
    public AbstractRelic makeCopy() { return new ReplicaLightning(); }
}