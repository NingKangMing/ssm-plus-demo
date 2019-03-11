package com.wholesmart.webcommon.util;

import java.util.ArrayList;
import java.util.List;

import com.wholesmart.entity.Area;
import com.wholesmart.entity.Department;

public class ZtreeUtiles {
	public static List<Node> DepartmentToZtreeJson(List<Department> list){
		List<Node> nodes = new ArrayList<Node>();
		for(int i= 0 ;i<list.size();i++) {
			Node node = new Node();
			node.setId(String.valueOf(list.get(i).getId()));
			node.setName(list.get(i).getName());
			node.setpId(list.get(i).getParentId());
			nodes.add(node);
		}
		return nodes;		
	}
	
	public static List<Node> AreaToZtreeJson(List<Area> list){
		List<Node> nodes = new ArrayList<Node>();
		for(int i= 0 ;i<list.size();i++) {
			Node node = new Node();
			node.setId(String.valueOf(list.get(i).getId()));
			node.setName(list.get(i).getName());
			node.setpId(list.get(i).getParentId());
			nodes.add(node);
		}
		return nodes;		
	}
	public static class Node{
		private String id;
		private String pId;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getpId() {
			return pId;
		}
		public void setpId(String pId) {
			this.pId = pId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}	
}

