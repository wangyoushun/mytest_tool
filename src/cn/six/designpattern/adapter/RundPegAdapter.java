package cn.six.designpattern.adapter;

public class RundPegAdapter implements IPeg {

	private IRoundPeg roundPeg;

	public RundPegAdapter(IRoundPeg roundPeg) {
		super();
		this.roundPeg = roundPeg;
	}

	@Override
	public void inSquare() {
		// TODO Auto-generated method stub
		System.out.println("adapter square");
	}
	
	public void inRoundPeg(){
		roundPeg.inRoundPeg();
	}

}
