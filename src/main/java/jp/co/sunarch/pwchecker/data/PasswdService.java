package jp.co.sunarch.pwchecker.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasswdService {

	@Autowired
	PasswdRepository repository;

	public TCommonPassEntity find(String password) {
		return repository.findByPassword(password);
	}

	public void save(TCommonPassEntity entity) {
		repository.save(entity);
	}
}
