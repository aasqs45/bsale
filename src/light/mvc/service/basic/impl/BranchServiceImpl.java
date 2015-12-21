package light.mvc.service.basic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Tree;
import light.mvc.model.auth.Tuser;
import light.mvc.model.basic.Tbranch;
import light.mvc.model.basic.Tbranchclass;
import light.mvc.pageModel.basic.Branch;
import light.mvc.service.basic.BranchServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchServiceI {
	
	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Autowired
	private BaseDaoI<Tbranch> branchDao;
	
	@Autowired
	private BaseDaoI<Tbranchclass> branchclassDao;

	@Override
	public List<Branch> treeGrid() {
		List<Branch> lr = new ArrayList<Branch>();
		List<Tbranch> l = branchDao
				.find("from Tbranch t left join fetch t.branchclass  order by t.seq");
		if ((l != null) && (l.size() > 0)) {
			for (Tbranch t : l) {
				Branch r = new Branch();
				BeanUtils.copyProperties(t, r);
				if (t.getBranch() != null) {
					r.setPid(t.getBranch().getId());
					r.setPname(t.getBranch().getName());
				}
				try {
					r.setBranchclassId(t.getBranchclass().getId());
					r.setBranchclassName(t.getBranchclass().getName());
				} catch (Exception e) {
				}
				r.setIconCls(t.getIcon());
				lr.add(r);
			}
		}
		return lr;
	}

	@Override
	public void add(Branch b) {
		Tbranch t = new Tbranch();
		BeanUtils.copyProperties(b, t);
		if ((b.getPid() != null) && !"".equals(b.getPid())) {
			t.setBranch(branchDao.get(Tbranch.class, b.getPid()));
		}
		if (b.getPid() != null && !"".equals(b.getPid())) {
			t.setBranchclass(branchclassDao.get(Tbranchclass.class, b.getBranchclassId()));
		}else{
			t.setBranchclass(branchclassDao.get(Tbranchclass.class, 0));
		}
		t.setCreatedatetime(new Date());
		branchDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tbranch t = branchDao.get(Tbranch.class, id);
		del(t);
	}

	private void del(Tbranch t) {
		List<Tuser> list = userDao.find("from Tuser t left join t.branch org where org.id="+t.getId());
		if(list!=null&&list.size()>0){
			throw new ServiceException("该部门已经被用户使用");
		}else{
			if ((t.getBranchs() != null) && (t.getBranchs().size() > 0)) {
				for (Tbranch r : t.getBranchs()) {
					del(r);
				}
			}
			branchDao.delete(t);
		}
	}

	@Override
	public void edit(Branch r) {
		Tbranch t = branchDao.get(Tbranch.class, r.getId());
		t.setCode(r.getCode());
		t.setIcon(r.getIcon());
		t.setName(r.getName());
		t.setSeq(r.getSeq());
		if ((r.getPid() != null) && !"".equals(r.getPid())) {
			t.setBranch(branchDao.get(Tbranch.class, r.getPid()));
		}
		branchDao.update(t);
	}

	@Override
	public Branch get(Long id) {
		Tbranch t = branchDao.get(Tbranch.class, id);
		Branch r = new Branch();
		BeanUtils.copyProperties(t, r);
		if (t.getBranch() != null) {
			r.setPid(t.getBranch().getId());
			r.setPname(t.getBranch().getName());
		}
		return r;
	}

	@Override
	public List<Tree> tree() {
		List<Tbranch> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = branchDao.find("select distinct t from Tbranch t order by t.seq");

		if ((l != null) && (l.size() > 0)) {
			for (Tbranch r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				if (r.getBranch() != null) {
					tree.setPid(r.getBranch().getId().toString());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				lt.add(tree);
			}
		}
		return lt;
	}

}
