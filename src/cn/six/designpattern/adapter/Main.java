package cn.six.designpattern.adapter;

/**
 * 适配器模式
 * 将接口不同但功能相近
 * 将一个类的接口转化为另一个接口，使由于接口不兼容不能一起工作的类一起工作
 * @author 有顺
 *
 */
public class Main {
	public static void main(String[] args) {
		IPeg peg  = new SquarPeg();
		peg.inSquare();
		
		RoundPeg roundPeg = new RoundPeg();
		IPeg  rundPegAdapter = new RundPegAdapter(roundPeg);
		rundPegAdapter.inSquare();
		((RundPegAdapter) rundPegAdapter).inRoundPeg();
	}
	
}
