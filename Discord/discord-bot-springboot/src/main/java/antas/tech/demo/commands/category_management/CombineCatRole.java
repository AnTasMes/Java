package antas.tech.demo.commands.category_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import antas.tech.demo.models.UserRole;
import antas.tech.demo.models.ServerCategory;
import antas.tech.demo.services.CategoryService;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component("combine_cat_role")
public class CombineCatRole extends SlashCommand {

    CategoryService categoryService;

    @Autowired
    public CombineCatRole(CategoryService categoryService) {
        this.name = "combine";
        this.help = "Adds a Owner role to a category";

        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "category", "ID of the Category").setRequired(true));
        options.add(new OptionData(OptionType.ROLE, "role", "Role to be added to the Category").setRequired(true));

        this.options = options;

        this.categoryService = categoryService;
    }

    @Override
    @SuppressWarnings("null")
    protected void execute(SlashCommandEvent event) {
        Role role = event.getOption("role").getAsRole();
        String categoryID = event.getOption("category").getAsString();

        net.dv8tion.jda.api.entities.channel.concrete.Category category = event.getGuild().getCategoryById(categoryID);

        if (category != null) {
            categoryService.bindCategoryRole(
                    new ServerCategory(categoryID, category.getName(), new UserRole(role.getId(), role.getName())));
        }
    }

}
