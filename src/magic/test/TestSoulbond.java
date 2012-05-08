package magic.test;

import magic.model.*;
import magic.model.action.MagicChangeCountersAction;
import magic.model.phase.MagicMainPhase;

// When a creature with soulbond enters the battlefield, you may pair it with
// another unpaired creature you control. When another creature enters the
// battlefield under your control, if you control a creature with soulbond
// that isn't currently paired, you may pair it with the new creature.

// The two creatures become unpaired if either of them leaves the battlefield,
// is no longer under your control, or stops being a creature.
class TestTest extends TestGameBuilder {    

    public MagicGame getGame() {
		final MagicDuel duel=new MagicDuel();
		duel.setDifficulty(6);
		
		final MagicPlayerProfile profile=new MagicPlayerProfile("bgruw");
		final MagicPlayerDefinition player1=new MagicPlayerDefinition("Player",false,profile,15);
		final MagicPlayerDefinition player2=new MagicPlayerDefinition("Computer",true,profile,14);
		duel.setPlayers(new MagicPlayerDefinition[]{player1,player2});
		duel.setStartPlayer(0);
		
		final MagicGame game=duel.nextGame(true);
		game.setPhase(MagicMainPhase.getFirstInstance());
		final MagicPlayer player=game.getPlayer(0);
		final MagicPlayer opponent=game.getPlayer(1);

        MagicPlayer P = player;

        P.setLife(12);
        addToLibrary(P,"Plains",15);
        createPermanent(game,P,"Rupture Spire",false,8);
		createPermanent(game,P,"Creeping Tar Pit",false,1);
		createPermanent(game,P,"Wingcrafter",false,1);
		createPermanent(game,P,"Wall of Stone",false,1);
		addToHand(P,"Wingcrafter",1);
		addToHand(P,"Barony Vampire",1);
	    addToHand(P,"Act of Treason",1);
	    addToHand(P,"Lightning Bolt",1);
	    addToHand(P,"Swamp",1);
	    addToHand(P,"Eager Cadet",1);
       
        P = opponent;
		
        P.setLife(12);
        addToLibrary(P, "Swamp", 15);
        createPermanent(game,P,"Rupture Spire",false,8);
		createPermanent(game,P,"Assault Griffin",false,1);
		createPermanent(game,P,"Hagra Diabolist",false,1);
		MagicPermanent la = createPermanent(game, P, "Legacy's Allure", false, 1);
		game.doAction(new MagicChangeCountersAction(la, MagicCounterType.Charge, 3, true));
	    addToHand(P,"Wingcrafter",1);
	    addToHand(P,"Eager Cadet",1);
		
		return game;
    }
}
