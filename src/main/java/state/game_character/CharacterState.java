package state.game_character;

public interface CharacterState {
    void train(GameCharacter character);

    void meditate(GameCharacter character);

    void fight(GameCharacter character);

    void displayAvailableActions();
}
