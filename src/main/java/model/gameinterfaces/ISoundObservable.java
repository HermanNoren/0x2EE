package model.gameinterfaces;

import sound.ISoundObserver;

import java.util.List;

public interface ISoundObservable {

    void subscribe(ISoundObserver observer);
    List<ISoundObserver> getSubscribedSoundObservevrs();

}
