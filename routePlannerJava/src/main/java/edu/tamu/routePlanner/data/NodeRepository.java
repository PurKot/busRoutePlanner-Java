package edu.tamu.routePlanner.data;

import java.util.*;
import java.io.*;

//Interface class for adding node items to DB
public interface NodeRepository<T> {
	public void Add(T t);

	public List<T> Get();

	public Boolean loadfromCSV() throws IOException;
}
