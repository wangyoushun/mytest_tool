package cn.six.designpattern.decorator;

public class LogReposity implements IGenericReposity {

	private IGenericReposity genericReposity;

	public LogReposity(IGenericReposity genericReposity) {
		super();
		this.genericReposity = genericReposity;
	}

	@Override
	public void save() {
		genericReposity.save();
		System.out.println("log save");
	}

	@Override
	public void update() {
		genericReposity.update();
		System.out.println("log update");
	}

}
