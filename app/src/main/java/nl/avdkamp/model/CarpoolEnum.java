package nl.avdkamp.model;

import nl.avdkamp.carpool.R;

/**
 * Created by vandekamp on 12-2-16.
 */
public enum CarpoolEnum{

    Meegereden(R.id.meegereden),
    ZelfGereden(R.id.zelfGereden),
    NietGewerkt(R.id.nietGewerkt),
    JaapMetMijMeegereden(R.id.jaapMetMijMeegereden);

    private int rId;

    CarpoolEnum(int rId) {
        this.rId = rId;
    }

    public int getRId()
    {
        return rId;
    }

    public static CarpoolEnum fromId(int rId)
    {
        for(CarpoolEnum carpoolEnum :CarpoolEnum.values())
        {
            if(carpoolEnum.getRId() == rId)
                return carpoolEnum;
        }
        return null;
    }


}
