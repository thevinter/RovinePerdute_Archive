public class Posizione
{
	private final int x, y, z;
	
	public Posizione(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	
	public double calcDistXY(Posizione p)
	{
		return (Math.sqrt(Math.pow((this.x - p.getX()), 2) + Math.pow((this.y - p.getY()), 2)));
	}
	
	public double calcDistH(Posizione p)
	{
		return Math.abs(this.z - p.getZ());
	}
}
