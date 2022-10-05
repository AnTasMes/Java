package antas.tech.demo.commands.category_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import antas.tech.demo.models.ServerCategory;
import antas.tech.demo.services.CategoryService;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component("add_category")
public class AddCategory extends SlashCommand {

    private CategoryService categoryService;

    @Autowired
    public AddCategory(CategoryService categoryService) {
        this.name = "addcat";
        this.help = "Maps an existing category to MongoDB";

        List<OptionData> options = new ArrayList<>();

        options.add(new OptionData(OptionType.STRING, "category", "Category ID").setRequired(true));

        this.options = options;

        this.categoryService = categoryService;
    }

    @Override
    @SuppressWarnings("null")
    protected void execute(SlashCommandEvent event) {
        String categoryID = event.getOption("category").getAsString();
        net.dv8tion.jda.api.entities.channel.concrete.Category category = event.getGuild()
                .getCategoryById(categoryID);

        if (category != null) {
            categoryService.createCategory(new ServerCategory(category.getName(), category.getId()));
        } else {
            event.reply("Category does not exist");
        }
    }

}
