package hr.ferit.converter;

public interface ButtonClickListener {
    void onButtonClick(String conversionType);
    void onConvertClick(String input);
    void onClearClick();
}
