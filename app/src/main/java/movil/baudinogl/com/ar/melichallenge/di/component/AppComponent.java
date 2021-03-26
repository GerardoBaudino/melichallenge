package movil.baudinogl.com.ar.melichallenge.di.component;

import javax.inject.Singleton;

import dagger.Component;
import movil.baudinogl.com.ar.melichallenge.di.module.AppModule;
import movil.baudinogl.com.ar.melichallenge.ui.activity.ProductPageActivity;
import movil.baudinogl.com.ar.melichallenge.ui.activity.SearchActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    //region # Activitys
    void inject(SearchActivity searchActivity);

    void inject(ProductPageActivity productPageActivity);
    //endregion
}