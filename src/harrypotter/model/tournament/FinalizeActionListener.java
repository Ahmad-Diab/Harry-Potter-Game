package harrypotter.model.tournament;

import harrypotter.model.character.Wizard;

public interface FinalizeActionListener {
	public void onChampionWin(Wizard champ);
	public void onChampionDeath(Wizard champ);
	public void onDragonFire(int x1,int y1,int x2, int y2);
	public void onEndingChampionTurn();
	public void onDecreasingCurrentHP();
	public void onUsePotion();
	void onMerpersonAttack(int i, int j);

}
