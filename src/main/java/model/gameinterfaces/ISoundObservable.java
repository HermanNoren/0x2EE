package model.gameinterfaces;

import sound.ISoundObserver;

public interface ISoundObservable {

    void subscribe(ISoundObserver observer);

}
