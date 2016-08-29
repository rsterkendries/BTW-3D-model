import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;


public class BTW_3D {
	
	private int[][][] pile;
	private final int Nx,Ny,Nz;
	private static final int FULL = 6;
	private HashSet<Position> visitedPositions = new HashSet<Position>();
	private ArrayList<Position> positionsToVisit = new ArrayList<Position>();
	private TreeMap<Integer,MutableInt> avalancheDistribution = new TreeMap<Integer,MutableInt>();
	
	public BTW_3D(int N1, int N2, int N3) {
		if(N1 <= 0 || N2 <= 0 || N3 <= 0)
			throw new IllegalArgumentException("arguments should be strictly positive integers");
		Nx = N1;
		Ny = N2;
		Nz = N3;
		pile = new int[Nx][Ny][Nz];
		
		populateArray();
	}
	
	public BTW_3D(int N) {
		this(N,N,N);
	}
	
	private void go() {
							
		positionsToVisit.add(randomShoot());
		Position position = null;
		int size;
		
		while(!(positionsToVisit.isEmpty())) {
			
				position = positionsToVisit.remove(0);
				visitedPositions.add(position);
				if(pile[position.getX()][position.getY()][position.getZ()] >= FULL) {
					
					pile[position.getX()][position.getY()][position.getZ()] -= FULL;
					spread(position);
			}			
		} 
		
		size = visitedPositions.size();
		MutableInt count = avalancheDistribution.get(size);
		
		if(count == null)
			avalancheDistribution.put(size, new MutableInt());
		else
			count.increment();
		
		visitedPositions.clear();
		positionsToVisit.clear();	
	}
	
	private void populateArray() {
		
		Random r = new Random();
		
		for(int i = 0; i < Nx; i++) {
			for(int j = 0; j < Ny; j++) {
				for(int k = 0; k < Nz; k++) {
					
					pile[i][j][k] = r.nextInt(FULL);
				}
			}
		}
	}
	
	private Position randomShoot() {
		
		Random r = new Random();		
		Position position = new Position(r.nextInt(Nx),r.nextInt(Ny),r.nextInt(Nz));		
		pile[position.getX()][position.getY()][position.getZ()]++;
		
		return position;
		
	}
	
	private void spread(Position position) {
		int x = position.getX();
		int y = position.getY();
		int z = position.getZ();
		Position p = null;
		
		if(x == 0 || x == Nx - 1) {
			spreadXYZ(x,y,z);
		}
		
		else if(y == 0 || y == Ny - 1) {
			spreadXYZ(x,y,z);
		}
		
		else if(z == 0 || z == Nz - 1) {
			spreadXYZ(x,y,z);
		}
		
		else {
			p = new Position(x+1,y,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x-1,y,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x,y+1,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x,y-1,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x,y,z+1);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x,y,z-1);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
	}
	
	private void spreadXYZ(int x, int y, int z) {
		spreadX(x,y,z);
		spreadY(x,y,z);
		spreadZ(x,y,z);
	}
	
	private void spreadX(int x, int y, int z) {
		Position p = null;
		if(x == 0) {
			p = new Position(x+1,y,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
		else if (x == Nx - 1) {
			p = new Position(x-1,y,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
		else {//x is on the edge
			p = new Position(x+1,y,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x-1,y,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
	}
	
	private void spreadY(int x, int y, int z) {
		Position p = null;
		if(y == 0) {
			p = new Position(x,y+1,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
		else if (y == Ny - 1) {
			p = new Position(x,y-1,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
		else {//y is on the edge
			p = new Position(x,y+1,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x,y-1,z);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
	}
	
	private void spreadZ(int x, int y, int z) {
		Position p = null;
		if(z == 0) {
			p = new Position(x,y,z+1);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
		else if (z == Nz - 1) {
			p = new Position(x,y,z-1);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
		else {//z is on the edge
			p = new Position(x,y,z+1);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
			p = new Position(x,y,z-1);
			positionsToVisit.add(p);
			pile[p.getX()][p.getY()][p.getZ()]++;
		}
	}
	
	public void startAvalanche(int volumeRatio) {
			int volume = Nx * Ny * Nz;
			for(int j = 1; j <= volumeRatio * volume; j++) {				
				go();
			}
	}
	
	public TreeMap<Integer,Integer> getAbsoluteDistribution() {
		avalancheDistribution.remove(1); //1cell avalanches are not avalanches
		TreeMap<Integer,Integer> Distribution = new TreeMap<Integer,Integer>();
		
		for(Entry<Integer, MutableInt> e : avalancheDistribution.entrySet()) {
			Distribution.put(e.getKey(),e.getValue().get());
		}
		
		return Distribution;
	}
	
	public TreeMap<Double,Integer> getRelativeDistribution() {
		avalancheDistribution.remove(1); //1cell avalanches are not avalanches
		TreeMap<Double,Integer> Distribution = new TreeMap<Double,Integer>();
		
		for(Entry<Integer, MutableInt> e : avalancheDistribution.entrySet()) {
			Distribution.put(((double)e.getKey())/(Nx * Ny * Nz),e.getValue().get());
		}
		
		return Distribution;
	}
	
	private class MutableInt {	
		//http://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
		private int count = 1;
		
		public void increment() {
			this.count++;
		}
		public int get() {
			return count;
		}	
		public String toString() {
			return new Integer(count).toString();
		}
	}

	private class Position {
		
		private int x;
		private int y;
		private int z;
		
		public Position(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getZ() {
			return z;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}
		
	}
}
