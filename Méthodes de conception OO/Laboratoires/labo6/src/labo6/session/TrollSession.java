package labo6.session;

import Profilers.Profiler;
import Profilers.TrollProfiler;
import labo6.Labo6Main;
import labo6.User;

public class TrollSession extends Session {

	public TrollSession(Labo6Main l, User u) {
		super(l, u);
	}

	public Profiler createProfiler() {
		return new TrollProfiler(human, ui);
	}
}
