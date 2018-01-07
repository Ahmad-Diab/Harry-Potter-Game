package harrypotter.model.tournament;

public interface SpellListener {
	public void onCastingDamagingSpell(int x ,int y);
	public void onRelocatingObstacle(int oldX,int oldY, int newX, int newY);
	public void onRelocatingChampion(int oldX,int oldY, int newX, int newY);
	public void onCastingHealinSpell();


}
