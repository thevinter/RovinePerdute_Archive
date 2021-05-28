package arnaldo.anno2021.triumvirato.rovineperdute;

import java.util.Comparator;

public class SentieroComparator implements Comparator<Sentiero>{

	@Override
	public int compare(Sentiero s1, Sentiero s2) {
		if( s1.getIdPartenza() > s2.getIdPartenza() ){
            return 1;
        }else if( s1.getIdPartenza() < s2.getIdPartenza() ){
            return -1;
        }else{
            return 0;
        }

	}

}
