package chronomuncher.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.helpers.PowerTip;

import com.badlogic.gdx.graphics.Texture;
import basemod.abstracts.CustomRelic;
import chronomuncher.ChronoMod;
import chronomuncher.orbs.*;
import chronomuncher.actions.*;

public class ReplicaNitrogen extends CustomRelic {
    public static final String ID = "Nitrogen?";

    public ReplicaNitrogen() {
        super(ID, new Texture("chrono_images/relics/Nitrogen.png"), new Texture("chrono_images/relics/outline/Nitrogen.png"), RelicTier.SPECIAL, LandingSound.CLINK);

        this.tips.add(new PowerTip("Nitrogen", "All #yreplicas last #g1 turns longer. NL #pShatters #pin #b6 #pturns."));
        this.initializeTips();
    }

    @Override
    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }
    
    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractDungeon.player.energy.use(1);
        AbstractDungeon.actionManager.addToBottom(new ChronoChannelAction(new UnlockedNitrogen(false)));
    }

    @Override
    public AbstractRelic makeCopy() { return new ReplicaNitrogen(); }
}