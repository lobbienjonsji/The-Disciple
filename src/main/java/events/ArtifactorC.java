package chronomuncher.events;

import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.relics.WarpedTongs;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import java.util.ArrayList;
import chronomuncher.relics.*;

public class ArtifactorC
  extends AbstractImageEvent
{
  public static final String ID = "ArtifactorC";
  private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("ArtifactorC");
  public static final String NAME = eventStrings.NAME;
  public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
  public static final String[] OPTIONS = eventStrings.OPTIONS;
  private static final String DIALOG_1 = DESCRIPTIONS[0];
  private static final String REPLICATE_RESULT = DESCRIPTIONS[1];
  private static final String LEAVE_RESULT = DESCRIPTIONS[2];
  private int screenNum = 0;
  private boolean pickCard = false;
  
  public ArtifactorC()
  {
    super(NAME, DIALOG_1, "chrono_images/events/ArtifactorC.png");
    this.imageEventText.setDialogOption(OPTIONS[0], CardLibrary.getCopy("LockedFlame"));
    this.imageEventText.setDialogOption(OPTIONS[1], CardLibrary.getCopy("LockedLightning"));
    this.imageEventText.setDialogOption(OPTIONS[2], CardLibrary.getCopy("LockedTornado"));
    this.imageEventText.setDialogOption(OPTIONS[3]);
  }
  
  public void onEnterRoom()
  {
    if (Settings.AMBIANCE_ON) {
      CardCrawlGame.sound.play("EVENT_FORGE");
    }
  }
  
  protected void buttonEffect(int buttonPressed)
  {
    switch (this.screenNum)
    {
    case 0: 
      switch (buttonPressed)
      {
      case 0: 
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new ReplicaFlame());
        
        this.imageEventText.updateBodyText(REPLICATE_RESULT);
        this.screenNum = 2;
        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
        break;
      case 1: 
        this.screenNum = 2;
        this.imageEventText.updateBodyText(REPLICATE_RESULT);
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new ReplicaLightning());
        
        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
        break;
      case 2: 
        this.screenNum = 2;
        this.imageEventText.updateBodyText(REPLICATE_RESULT);
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new ReplicaTornado());
        
        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
        break;
      case 3: 
        this.screenNum = 2;
        this.imageEventText.updateBodyText(LEAVE_RESULT);
        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
      }
      this.imageEventText.clearRemainingOptions();
      break;
    default: 
      openMap();
    }
  }
}

        // AbstractCard curse = new Pain();
        // AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(curse, Settings.WIDTH / 2, Settings.HEIGHT / 2));
