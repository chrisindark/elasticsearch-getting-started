package modules;

import com.google.inject.AbstractModule;
import services.ElasticService;
import services.UserService;
import services.impl.ElasticServiceImpl;
import services.impl.UserServiceImpl;

public class ServiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton();
        bind(ElasticService.class).to(ElasticServiceImpl.class).asEagerSingleton();
    }
}
