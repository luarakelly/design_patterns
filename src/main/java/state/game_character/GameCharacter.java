package state.game_character;

public class GameCharacter {
    private String name;
    private int experiencePoints;
    private int healthPoints;
    private CharacterState state;

    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.healthPoints = 100;

        this.state = new NoviceState();
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }

    public CharacterState getState() {
        return state;
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

}
