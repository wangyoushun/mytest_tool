package cn.six.designpattern.decorator;

public class GenericReposityImpl implements IGenericReposity {

	@Override
	public void save() {
		System.out.println("save dao");
	}

	@Override
	public void update() {
		System.out.println("update dao");
	}

}
