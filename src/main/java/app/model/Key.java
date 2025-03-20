package model;

public record Key(String keyId, String finger, int row, int col) {
    public boolean isLeftHand() {
        return finger.startsWith("LEFT");
    }
}
