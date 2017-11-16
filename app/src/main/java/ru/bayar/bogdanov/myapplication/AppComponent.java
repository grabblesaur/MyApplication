package ru.bayar.bogdanov.myapplication;

import javax.inject.Singleton;

import dagger.Component;
import ru.bayar.bogdanov.myapplication.ui.cards.CardsFragment;

/**
 * Created by android on 16.11.17.
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(CardsFragment fragment);
}
