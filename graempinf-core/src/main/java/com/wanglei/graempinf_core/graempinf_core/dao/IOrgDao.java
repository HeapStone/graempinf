package com.wanglei.graempinf_core.graempinf_core.dao;

import java.util.List;

import com.wanglei.basic.hibernate.dao.IBaseDao;
import com.wanglei.basic.hibernate.model.Pager;
import com.wanglei.graempinf_core.graempinf_core.model.Org;
import com.wanglei.graempinf_core.graempinf_core.model.SelectUtils;

public interface IOrgDao extends IBaseDao<Org> {
	/**
	 * <p>Description:分页显示组织机构信息<p>
	 * @param pid
	 * @param typeId
	 * @return
	 * @author WangLei 2017年2月8日
	 */
	public Pager<Org> findByParent(Integer pid,Integer typeId);
	/**
	 * 根据组织类型来生成这颗树，如果tid为null，就获取所有的组织
	 * @param tid
	 * @return
	 */
	public List<Tree> tree();
	/**
	 * 根据组织类型，获取该父节点下究竟有多少个子组织，在添加组织的时候可以确定是否可以添加
	 * @param pid
	 * @param type
	 * @return
	 */
	public int loadNumByType(Integer pid,int typeId);
	
	/**
	 * 获取某个组织下面的所在子组织id
	 * 需要进行判断，如果组织类型是NO_TYPE-->return null
	 * 如果组织类型是DEFAULT_TYPE-->返回该组织的所有子节点
	 * 如果组织类型是ALL_TYPE-->返回所有组织
	 * 如果组织类型是DEF_TYPE-->获取所有子组织下的子节点
	 * @param id
	 * @return
	 */
	public List<Integer> listAllChildIdsByOrg(int id);
	/**
	 * 获取某个组织下面的所有直线管理的子组织
	 * @param id
	 * @return
	 */
	public List<Integer> listChildIdsByOrg(int id);
	
	public List<Org> listAllChildByOrg(int id);
	
	public List<Org> listChildByOrg(int id);
	/**
	 * 获取某个子组织下的节点树
	 * @param id
	 * @return
	 */
	public List<Tree> listChildTreeByOrg(int id);
	/**
	 * 获取某个子组织下的所有的组织，也需要根据类型来进行获取
	 * @param id
	 * @return
	 */
	public List<Tree> listAllChildTreeByOrg(int id);
	/**
	 * 根据一组ids获取树对象
	 * @param ids
	 * @return
	 */
	public List<Tree> listChildTreeByOrgs(List<Integer> ids);
	/**
	 * 根据一个组织机构类型的编号获取父类的所有的组织机构节点，并且生成一棵树
	 * @param sn
	 * @return
	 */
	public List<Tree> listParentTreeByOrgType(String sn);
	
	public int getMaxOrder(Integer pid);
	/**
	 * 根据组织机构类型id获取当前的组织数
	 * @param typeId
	 * @return
	 */
	public int getOrgNumsByType(int typeId);
	
	/**
	 * 获取某个人所在的所有组织
	 * @param pid
	 * @return
	 */
	public List<Org> listPersonOrg(int pid);
	public List<Org> listChilds(int pid);
	public List<SelectUtils> createOrForSelect(Integer id,Integer typeId);
	public Org getOrgNameById(Integer id);
}
