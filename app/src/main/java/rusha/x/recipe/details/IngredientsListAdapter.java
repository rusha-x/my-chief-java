package rusha.x.recipe.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import rusha.x.R;
import rusha.x.databinding.RecipeDetailsItemBinding;
import rusha.x.recipe.RecipeIngredient;

/**
 * Преобразует список [ingredientsToAdopt] в отображение списка в [ingredientsView]
 */
public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientViewHolder> {

    private RecipeDetailsViewModel viewModel;

    public IngredientsListAdapter(RecipeDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Список который адаптирует [IngredientsListAdapter]
     */
    private List<RecipeIngredient> ingredientsToAdopt = Collections.emptyList();

    public void setIngredientsToAdopt(List<RecipeIngredient> ingredients) {
        ingredientsToAdopt = ingredients;
        notifyDataSetChanged();
    }

    /**
     * Создать ячейку для RecyclerView, лежащего в [parent]
     */
    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Получить [LayoutInflater] из текущего контекста
        // который можно получить из любого отображения в этом контексте
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // "Надуть" [R.layout.recipe_details_item] так чтобы потом поместить его в [parent]
        // Не нужно привязывать его к [parent] сразу. Поэтому 3 аргумет = false
        View view = inflater.inflate(
                // R - ресурсы (это то, что может меняться в зависимости от того, на каком
                // устройсте запущено приложение. Например, мы можем загрузить в приложение
                // иконки разного качества. В зависимости от плотности точек экрана, будут
                // использоваться разные иконки)
                //
                // layout - папка с файлами разметки. Можно использовать разные разметки для
                // разных экранов или отдельную для вертикального и горизонтального положений
                // экрана
                //
                // recipe_details_item - название файла надуваемой разметки (верстки)
                R.layout.recipe_details_item,
                // В какое отображение будет помещено отображение, полученное в результате
                // надувания вёрстки
                parent,
                // Нужно ли сразу прикрепить надутую вёрстку к parent
                false
        );

        RecipeDetailsItemBinding binding = RecipeDetailsItemBinding.bind(view);

        // Создать держатель отображения у которого в качестве отображения
        // будет значение переменной [view]
        // Держатель отображения будет результатом вызова функции [onCreateViewHolder].
        // RecyclerView вызовет эту функцию и положит ячейку, полученную в результате
        // в пул ячеек, некоторые из которых некоторые сразу заполнит и отобразит пользователю,
        // а некоторые - будет держать "на изготове"
        return new IngredientViewHolder(viewModel, binding);
    }

    /**
     * Попросить держатель отображения ячейки [holder] заполнить отображение внутри него
     * элементом из списка [ingredientsToAdopt] по позиции [position]
     */
    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        // Получить ингридиент по позиции [position]
        RecipeIngredient ingredientOnPosition = ingredientsToAdopt.get(position);

        // Попросить ячейку заполниться этим игредиентом
        holder.bind(ingredientOnPosition);
    }

    /**
     * Сделать так чтобы [onBindViewHolder] не вызвалась с позицией (position)
     * которой нет в списке
     */
    @Override
    public int getItemCount() {
        return ingredientsToAdopt.size();
    }
}