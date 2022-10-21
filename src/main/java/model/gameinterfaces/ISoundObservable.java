package model.gameinterfaces;

import sound.ISoundObserver;

import java.util.List;

public interface ISoundObservable {

    void subscribeToSoundEvent(ISoundObserver observer);
    List<ISoundObserver> getSubscribedToSoundEvent();

}
