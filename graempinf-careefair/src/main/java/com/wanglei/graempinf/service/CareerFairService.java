package com.wanglei.graempinf.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import CareerFairAppointment.ICapDao;

import com.wanglei.basic.hibernate.model.Pager;
import com.wanglei.basic.util.DateUtils;
import com.wanglei.graempinf_core.graempinf_core.dao.ICareerFairDao;
import com.wanglei.graempinf_core.graempinf_core.model.CareerFair;
import com.wanglei.graempinf_core.graempinf_core.model.CareerFairAppointment;
import com.wanglei.graempinf_core.graempinf_core.model.GraEmpInfException;
@Service("careerFairService")
public class CareerFairService implements ICareerFairService {
	private ICareerFairDao careerFairDao;
	private ICapDao capDao;
	
	public ICareerFairDao getCareerFairDao() {
		return careerFairDao;
	}
	@Inject
	public void setCareerFairDao(ICareerFairDao careerFairDao) {
		this.careerFairDao = careerFairDao;
	}

	public ICapDao getCapDao() {
		return capDao;
	}
	@Inject
	public void setCapDao(ICapDao capDao) {
		this.capDao = capDao;
	}

	@Override
	public void add(CareerFair cf) {
		careerFairDao.add(cf);
	}

	@Override
	public void delete(String id) {
		//查询是否有预约如果有预约则不允许删除
		List<CareerFairAppointment> temcap = capDao.loadByCfid(id);
		if (null!=temcap && temcap.size()>0 ){
			throw new GraEmpInfException("不能删除招聘会信息，已经存在预约！");
		}else{
			careerFairDao.deleteCareerFair(id);
		}
	}

	@Override
	public void update(CareerFair cf) {
		//查询是否有发布如果已经发布则不予许修改
		CareerFair cftem = careerFairDao.loadCareerFairByUUid(cf.getCareerFairUuid());
		if(null!=cftem){
			Integer cfStatus = cftem.getFinshStatus();
			if (cfStatus==1){
				throw new GraEmpInfException("不能修改招聘会信息，已经发布！");
			}else{
				cftem.setCareerFairName(cf.getCareerFairName());
				cftem.setCareerFairDesc(cf.getCareerFairDesc());
				cftem.setCareerFairType(cf.getCareerFairType());
				cftem.setCareerFairTypeName(cf.getCareerFairName());
				cftem.setCareerFairDate(cf.getCareerFairDate());
				cftem.setCareerFairAddr(cf.getCareerFairAddr());
				cftem.setCareerFairUndertaker(cf.getCareerFairUndertaker());
				careerFairDao.updateCareerFair(cftem);
			}
		}else{
			throw new GraEmpInfException("不能修改招聘会信息，不存在该招聘会信息！");
		}
		
	}

	@Override
	public CareerFair load(String uuid) {
		return careerFairDao.loadCareerFairByUUid(uuid);
	}

	@Override
	public Pager<CareerFair> findByPager(CareerFair cf) {
		return careerFairDao.findBypager(cf);
	}

	@Override
	public List<CareerFair> listCareerFair(CareerFair cf) {
		return careerFairDao.listCareerFair(cf);
	}
	@Override
	public void updateCareerFair(CareerFair cf) {
		//如果发布时间大于小于当前时间不让发布
		if(cf.getCareerFairDate().after(DateUtils.getCurrentSqlDate())){
			throw new GraEmpInfException("不能发布招聘会信息，该发布会已经举行过！");	
		}else{
			careerFairDao.update(cf);
			careerFairDao.updateCareerFairStatus(cf.getCareerFairUuid(), 1);
		}
	}
	@Override
	public void updateCareerFair(String uuid) {
		List<CareerFairAppointment> temcap = capDao.loadByCfid(uuid);
		if (null!=temcap && temcap.size()>0 ){
			throw new GraEmpInfException("不能取消发布招聘会信息，已经存在预约！");
		}
		else{
				careerFairDao.updateCareerFairStatus(uuid, 0);
			}
	}

}
