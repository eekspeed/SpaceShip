package Game;

public enum Dir {
    L, R, U, D;

    public static Dir getDir(String s){

        if(s.equals("L")){
            return L;
        } else if(s.equals("R")){
            return R;
        } else if(s.equals("U")){
            return U;
        } else if(s.equals("D")){
            return D;
        }

        return null;
    }
}


