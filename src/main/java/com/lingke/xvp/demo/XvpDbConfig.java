package com.lingke.xvp.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.lingke.xvp.demo.db.dao._MappingKit;

/**
 * Created by yuwb on 2017-3-14.
 */
@Component
public class XvpDbConfig  implements InitializingBean{
	@Value("${com.lingke.xvp.db.url}")
    private String jdbcUrl;
	@Value("${com.lingke.xvp.db.user}")
    private String dbUser;
	@Value("${com.lingke.xvp.db.password}")
    private String dbPassword;
	@Value("${com.lingke.xvp.db.name}")
	private String dbName;
    private int initialSize = 1;
    private int minIdle = 1;
    private int maxActive = 20;
    private int maxWait = 30000;
	@Override
	public void afterPropertiesSet() throws Exception {
        DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, dbUser,dbPassword);
        druidPlugin.setInitialSize(initialSize);
        druidPlugin.setMinIdle(minIdle);
        druidPlugin.setMaxActive(maxActive);
        druidPlugin.setMaxWait(maxWait);
        druidPlugin.start();
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(dbName, druidPlugin.getDataSource());
        _MappingKit.mapping(activeRecordPlugin);
        activeRecordPlugin.start();
		
	}
}
