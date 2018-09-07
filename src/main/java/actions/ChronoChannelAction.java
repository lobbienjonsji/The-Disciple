package chronomuncher.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class ChronoChannelAction
  extends AbstractGameAction
{
  private AbstractOrb orbType;
  private boolean autoEvoke = false;
  
  public ChronoChannelAction(AbstractOrb newOrbType)
  {
    this(newOrbType, true);
  }
  
  public ChronoChannelAction(AbstractOrb newOrbType, boolean autoEvoke)
  {
    this.duration = Settings.ACTION_DUR_FAST;
    this.orbType = newOrbType;
    this.autoEvoke = autoEvoke;
  }
  
  public void update()
  {
    if (AbstractDungeon.player.maxOrbs == 10) {
      AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "#rYou cannot fit any more replicas for now.", true));

      this.isDone = true;
      return;
    }
    if (this.duration == Settings.ACTION_DUR_FAST)
    {
      if (this.autoEvoke) {
        AbstractDungeon.player.channelOrb(this.orbType);
      } else {
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
          if ((o instanceof EmptyOrbSlot))
          {
            AbstractDungeon.player.channelOrb(this.orbType);
            break;
          }
        }
      }
      this.isDone = true;
      return;
    }
    tickDuration();
  }
}