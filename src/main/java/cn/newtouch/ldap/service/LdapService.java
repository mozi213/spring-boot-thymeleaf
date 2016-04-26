package cn.newtouch.ldap.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LdapService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());


	/**
	 * 修改密码服务
	 * @author haijiang.mo
	 * @since 2016年4月12日
	 * @param uid
	 * @param oldPasswd
	 * @param newPasswd
	 * @throws IllegalArgumentException
	 */
	public void modifyPassword(String uid, String oldPasswd, String newPasswd) throws IllegalArgumentException {
		Assert.notNull(uid, "登录名不能为空");
		Assert.notNull(oldPasswd, "密码不能未空");
		Assert.notNull(newPasswd, "新密码不能未空");

		// 校验原密码
		try {
			if("zhangsan".equals(uid)&&"123456".equals(oldPasswd)){
				logger.info("校验用户密码成功");
			}else{
				throw new IllegalArgumentException("密码错误");
			}
			logger.info("设置用户密码:{}",newPasswd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalArgumentException("用户不存在或密码错误");
		}
	}

}
