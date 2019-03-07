package dao;

import model.Local;

import java.util.List;

public interface LocalDao {


	public void update(Local user);
	public List<Local> getLocal();
	public Local getLocalById(int id);
}