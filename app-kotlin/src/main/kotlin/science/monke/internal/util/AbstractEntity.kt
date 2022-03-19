package science.monke.internal.util

import org.springframework.data.annotation.Id
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity<T : Serializable> : Serializable {

    @Id
    var id: T? = null

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as AbstractEntity<*>

        return if (null == this.id) false else this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}