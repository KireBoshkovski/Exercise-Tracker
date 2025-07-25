package com.sorsix.exercisetracker.service.specification

import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class FieldFilterSpecification {
    companion object {

        fun <T, V : Comparable<V>> greaterThan(clazz: Class<T>, field: String, value: V?): Specification<T>? {
            if (value == null) return null

            return Specification { root, _, criteriaBuilder ->
                val path = fieldToPath<V>(field, root as Root<*>)
                criteriaBuilder.greaterThan(path, value)
            }
        }

        fun <T, V : Comparable<V>> lessThan(clazz: Class<T>, field: String, value: V?): Specification<T>? {
            if (value == null) return null

            return Specification { root, _, criteriaBuilder ->
                val path = fieldToPath<V>(field, root as Root<*>)
                criteriaBuilder.lessThan(path, value)
            }
        }

        fun <T, V> filterEqualsT(clazz: Class<T>, field: String, value: V?): Specification<T>? {
            if (value == null) return null

            return Specification { root, _, criteriaBuilder ->
                val path = fieldToPath<V>(field, root as Root<*>)
                criteriaBuilder.equal(path, value)
            }
        }

        private fun <V> fieldToPath(field: String, root: Root<*>): Path<V> {
            val parts = field.split(".")
            var path: Path<*> = root
            for (part in parts) {
                path = path.get<Any>(part)
            }
            @Suppress("UNCHECKED_CAST")
            return path as Path<V>
        }
    }
}