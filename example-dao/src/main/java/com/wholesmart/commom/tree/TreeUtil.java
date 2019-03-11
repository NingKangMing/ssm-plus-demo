package com.wholesmart.commom.tree;

import java.util.ArrayList;
import java.util.List;

import com.wholesmart.common.util.StringUtils;
import com.wholesmart.entity.Resource;



public class TreeUtil {
	
	/*
	 * select2下拉组件数据对象
	 */
	private List<SelectTwoEntity> selectTree = new ArrayList<SelectTwoEntity>();
	/*
	 * 生成select2下拉组件数据时遍历的次数
	 */
	private int selectCnt = 0;

	public List<TreeEntity> generateTree(List<Resource> resourceList)
	{
		List<TreeEntity> jstreeList = new ArrayList<TreeEntity>();
		
		for (Resource resource : resourceList) {
			TreeEntity jstree = new TreeEntity();
			jstree.setId(resource.getId().toString());
			jstree.setParent(resource.getParentId()==0?"#":resource.getParentId().toString());
			jstree.setText(resource.getSourceName());
			jstree.setIcon(StringUtils.isBlank(resource.getIcon()) ? "am-icon-cog" : resource.getIcon());
			TreeEntity.State state = new TreeEntity().new State();
			state.setDisabled(false);
			state.setSelected(resource.isChecked());
			state.setOpened(true);
			jstree.setState(state);
			jstreeList.add(jstree);
		}
		return jstreeList;
	}
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list	具有树形结构特点的集合
	 * @param parent	父节点ID
	 * @return	树形结构集合
	 */
	public List<SelectTwoEntity> getSelectTree(List<Resource> list,Long parentId) {
		List<Resource> treeMenuList = treeMenuList(list, parentId);
		recursionForSelect(treeMenuList);
		return selectTree;
	}
	
	/**
	 * 递归列表
	 * @param list
	 * @param t
	 */
	private void recursionForSelect(List<Resource> list) {
		String str = "";
		for(int i=0; i< selectCnt; i++)
		{
			str += "├┈┈┈";
		}
		for (Resource re : list) {
			if(null == re.getParentId())
			{
				str = "";
				selectCnt = 0;
			}
			SelectTwoEntity se = new SelectTwoEntity();
			se.setId(re.getId().toString());
			se.setText(str + re.getSourceName());
			se.setName(re.getSourceName());
			selectTree.add(se);
			if(re.getChildren().size() > 0)
			{
				selectCnt ++;
				recursionForSelect(re.getChildren());
			}
		}
	}
	
	/**
	 * 菜单树递归
	 * @param menuList
	 * @param parentId
	 * @return
	 */
	public List<Resource> treeMenuList(List<Resource> menuList, Long parentId) {  
		 List<Resource> childMenu = new ArrayList<Resource>();  
	       for (Resource menu : menuList) {  
	    	   Long menuId = menu.getId();
	    	   Long pid = menu.getParentId();
	           if (parentId == pid) {  
	        	   List<Resource> c_node = treeMenuList(menuList, menuId);  
	        	   menu.setChildren(c_node);
	               childMenu.add(menu);  
	           }  
	       }  
	       return childMenu;  
	  }  
	
}
