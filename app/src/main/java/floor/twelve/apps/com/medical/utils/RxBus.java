package floor.twelve.apps.com.medical.utils;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Alexandra on 05.07.2017.
 */

public class RxBus {

  private final PublishSubject<Object> mBusSubject;

  public RxBus() {
    mBusSubject = PublishSubject.create();
  }

  public void post(Object event) {
    mBusSubject.onNext(event);
  }

  public Observable<Object> observable() {
    return mBusSubject;
  }

  public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
    return mBusSubject.ofType(eventClass);
  }

  public boolean hasObservers() {
    return mBusSubject.hasObservers();
  }
}
