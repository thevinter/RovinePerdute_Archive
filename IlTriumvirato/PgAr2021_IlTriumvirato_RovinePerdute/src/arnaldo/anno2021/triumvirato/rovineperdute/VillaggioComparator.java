package arnaldo.anno2021.triumvirato.rovineperdute;

import java.util.Comparator;

public class VillaggioComparator implements Comparator<Villaggio>{

	@Override
	public int compare(Villaggio v1, Villaggio v2) {
		if( v1.getId() > v2.getId() ){
            return 1;
        }else if( v1.getId() < v2.getId() ){
            return -1;
        }else{
            return 0;
        }

	}

}
