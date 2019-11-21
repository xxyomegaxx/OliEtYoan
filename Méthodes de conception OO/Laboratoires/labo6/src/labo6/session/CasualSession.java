package labo6.session;

import Profilers.CasualProfile;
import Profilers.Profiler;
import labo6.Labo6Main;
import labo6.User;


public class CasualSession extends Session {

	public CasualSession(Labo6Main l, User u) {
		super(l, u);
	}

	public Profiler createProfiler() {
		return new CasualProfile(human,ui);
	}

}
